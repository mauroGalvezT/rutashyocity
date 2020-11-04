<?php
require_once 'database/conexion.php';
require_once 'respuestas.class.php';

class signupdriver extends conexion{
    private $table_name = "tbl_conductor";
    private $nombres;
    private $apellidos;
    private $telefono;
    private $CON_Direccion;
    private $CON_Licencia;
    private $empresaId;
    private $CON_Latitud;
    private $CON_Longitud;
    private $CON_Status;
    private $CON_FCM;
    private $correo;
    private $pass;

    public function post($json){
        $_respuestas = new respuestas;
        $datos = json_decode($json, true);
        if(!isset($datos['nombres']) || !isset($datos['apellidos']) || !isset($datos['correo']) || !isset($datos['password']) || !isset($datos['empresaId']) || !isset($datos['telefono'])){
            return $_respuestas->error_400();
        }else{
            $this->nombres = $datos['nombres'];
            $this->apellidos = $datos['apellidos'];
            $this->correo = $datos['correo'];
            $this->empresaId = $datos['empresaId'];
            $this->telefono = $datos['telefono'];
            $password = $datos['password'];

            $this->pass = parent::encriptar($password);
            $resp = $this->registrarUsuarioDriver();
            
            if($resp){
                $signup = array(
                    "status" => true,
                    "message" => "Registro correto"     
                );
                return $signup;

            }else{
                return $_respuestas->error_500();
            }
        }
    }

    private function registrarUsuarioDriver(){
        $query = "INSERT INTO " . $this->table_name . "(CON_Nombre,CON_Apellidos,CON_Email,CON_Contrasena,ID_Empresa_Transp,CON_Telefono) values ('" . $this->nombres ."','". $this->apellidos ."','" . $this->correo."','". $this->pass ."','". $this->empresaId ."','". $this->telefono ."')";
        $resp = parent::nonQueryId($query);
        if($resp){
            return $resp;
        }else{
            return 0;
        }

    }
}