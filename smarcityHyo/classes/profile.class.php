<?php
require_once 'database/conexion.php';
require_once 'respuestas.class.php';

class profile extends conexion
{
    private $table = "Tbl_Usuario";
    private $token = "";
    public $ID_Usuario;
    public $US_Nombres;
    public $US_Apellidos;
    public $US_Direccion;
    public $US_Fecha_Nacimiento;
    public $US_Nacionalidad;
    public $US_Telefono;
    public $US_Email;
    public $US_Contrasena;
    public $US_Tipo;

    

    public function get($json)
    {
        $_respuestas = new respuestas;
        $datos = json_decode($json, true);
        if (!isset($datos['token'])) {
            return $_respuestas->error_401();
        } else {
            $this->token = $datos['token'];
            $arrayToken = $this->buscarToken();
            if ($arrayToken) {
                $resp = $this->mostrarPerfil($arrayToken[0]["ID_Usuario"]);//$datos[0]["ID_USUARIO"]
                //($arrayToken[0]["ID_Usuario"]) -> captura el id del token de la bd
                if ($resp) {
                    /*
                    $respuesta = $_respuestas->response;
                    $respuesta["result"] = array(
                        "US_usuarioId" => $this->ID_Usuario
                    );
                    */
                    //echo 'hola';
                    $user_arr = array(
                        "status" => true,
                        "id" => $resp[0]['ID_Usuario'],
                        "nombres" => $resp[0]['US_Nombres'],
                        "apellido" => $resp[0]['US_Apellidos'],
                        "correo" => $resp[0]['US_Email'],
                        "telefono" => $resp[0]['US_Telefono'],
                        "direccion" => $resp[0]['US_Direccion'],
                        "nacionalidad" => $resp[0]['US_Nacionalidad'],
                        //"id_user" => $datos[0]['ID_Usuario'],
                        //"token" => $resp
                    );
                    return $user_arr;
                }else{
                    return $_respuestas->error_400();
                }
            }else{
                return $_respuestas->error_400();
            }
        }
    }
    private function buscarToken()
    {
        $query = "SELECT  TokenId,ID_Usuario,Estado from usuarios_token WHERE Token = '" . $this->token . "' AND Estado = 'Activo'";
        $resp = parent::obtenerDatos($query);
        if ($resp) {
            return $resp;
        } else {
            return 0;
        }
    }
    public function mostrarPerfil($usuarioid)
    {
        $query = "SELECT 
        ID_Usuario, 
        US_Nombres, 
        US_Apellidos, 
        US_Direccion, 
        US_Fecha_Nacimiento, 
        US_Nacionalidad, 
        US_Telefono, 
        US_Email 
        FROM " . $this->table . "
        WHERE ID_Usuario =  '$usuarioid'";

        $datos = parent::obtenerDatos($query);
        return($datos);
    }
}
