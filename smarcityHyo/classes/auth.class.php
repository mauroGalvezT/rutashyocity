<?php
require_once 'database/conexion.php';
require_once 'respuestas.class.php';

class auth extends conexion
{
    public function login($json)
    {
        $_respuestas = new respuestas;
        $datos = json_decode($json, true); //convertir de json a array
        if (!isset($datos['usuario']) || !isset($datos['password'])) {
            //error en los campos
            return $_respuestas->error_400();
        } else {
            //status 200
            $usuario = $datos['usuario'];
            $password = $datos['password'];
            $password = parent::encriptar($password);
            $datos = $this->obtenerDatosUsuario($usuario);
            if ($datos) {
                //verificar la contraseña es igual
                if ($password == $datos[0]['US_Contrasena']) {
                    if ($datos[0]['Estado'] == "Activo") {

                        //crear token
                        $verificar = $this->insertarToken(($datos[0]['ID_Usuario']));
                        if ($verificar) {
                            //si se guardo
                            /*
                            $result = $_respuestas->response;
                            $result["result"] = array(
                                "token" => $verificar
                            );
                            return $result;
*/

                            $user_arr = array(
                                "status" => true,
                                "id_user" => $datos[0]['ID_Usuario'],
                                "estado" => $datos[0]['Estado'],
                                "token" => $verificar
                            );
                            return $user_arr;
                        } else {
                            return $_respuestas->error_500("error interno, no hemos podido guardar");
                        }
                    } else {
                        //el usuario esta inactivo
                        return $_respuestas->error_200("el usuario esta inactivo");
                    }
                } else {
                    return $_respuestas->error_200("El usuario pass es invalido");
                }
            } else {
                //no existe el usuario
                return $_respuestas->error_200("El usuario $usuario no existe");;
            }
        }
    }

    private function obtenerDatosUsuario($correo)
    {
        $query = "SELECT ID_Usuario,US_Contrasena,Estado,US_Telefono FROM tbl_usuario WHERE US_Email  = '$correo'";
        $datos = parent::obtenerDatos($query);

        if (isset($datos[0]["ID_Usuario"])) {
            return $datos;
        } else {
            return 0;
        }
    }
    private function insertarToken($usuarioid)
    {
        $val = true;
        $token = bin2hex(openssl_random_pseudo_bytes(16, $val));
        $date = date("Y-m-d H:i");
        $estado = "Activo";
        //si el token existe metodo remplazar
        $query = "INSERT INTO usuarios_token (ID_Usuario, Token, Estado, Fecha) VALUES('$usuarioid','$token', '$estado', '$date')";
        $verificar = parent::nonQuery($query);
        if ($verificar) {
            return $token;
        } else {
            return false;
        }
    }
}
