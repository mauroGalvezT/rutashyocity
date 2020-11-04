<?php
require_once 'database/conexion.php';
require_once 'respuestas.class.php';


class logindriver extends conexion{
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
            $datos = $this->obtenerDatosUsuarioDriver($usuario);
            if ($datos) {
                //verificar la contraseña es igual
                if ($password == $datos[0]['CON_Contrasena']) {
                   
                        //crear token
                        $verificar = $this->insertarToken(($datos[0]['ID_Conductor']));
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
                                "token" => $verificar,
                                "id_user" => $datos[0]['ID_Conductor'],
                                "id_empresa_trasnporte" => $datos[0]['ID_Empresa_Transp'],
                                "nombre" => $datos[0]['CON_Nombre'],
                                "apellido" => $datos[0]['CON_Apellidos']

                            );
                            return $user_arr;
                        } else {
                            return $_respuestas->error_500("error interno, no hemos podido guardar");
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

    private function obtenerDatosUsuarioDriver($correo)
    {
        $query = "SELECT ID_Conductor, 	CON_Contrasena, ID_Empresa_Transp, CON_Nombre, CON_Apellidos  FROM tbl_conductor WHERE CON_Email   = '$correo'";
        $datos = parent::obtenerDatos($query);

        if (isset($datos[0]["ID_Conductor"])) {
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
        $query = "INSERT INTO driver_token (ID_Conductor, Token, Estado, Fecha) VALUES('$usuarioid','$token', '$estado', '$date')";
        $verificar = parent::nonQuery($query);
        if ($verificar) {
            return $token;
        } else {
            return false;
        }
    }
}