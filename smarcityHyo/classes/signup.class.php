<?php
require_once 'database/conexion.php';
require_once 'respuestas.class.php';

class signup extends conexion{

    private $table_name = "Tbl_Usuario";
  
    // object properties
    private $ID_Usuario;
    private $nombres;
    private $apellidos;
    private $US_Direccion;
    private $US_Fecha_Nacimiento;
    private $US_Nacionalidad;
    private $telefono;
    private $correo;
    private $password;
    private $tipo;
    private $estado;
    private $pass;

    public function post($json){
        $_respuestas = new respuestas;
        $datos = json_decode($json, true);
        if(!isset($datos['nombres']) || !isset($datos['apellidos']) || !isset($datos['correo']) || !isset($datos['password']) || !isset($datos['telefono']) || !isset($datos['tipo']) || !isset($datos['estado'])){
            return $_respuestas->error_400();
        }else{
            $this->nombres = $datos['nombres'];
            $this->apellidos = $datos['apellidos'];
            $this->telefono = $datos['telefono'];
            $this->correo = $datos['correo'];
            $this->tipo = $datos['tipo'];
            $this->estado = $datos['estado'];
            $password = $datos['password'];

            $this->pass = parent::encriptar($password);
            $resp = $this->registrarUsuario();
            
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

    private function registrarUsuario(){
        $query = "INSERT INTO " . $this->table_name . "(US_Nombres, US_Apellidos,US_Email, US_Contrasena,US_Telefono, US_Tipo,Estado) values ('" . $this->nombres ."','". $this->apellidos ."','" . $this->correo."','". $this->pass ."','" . $this->telefono . "','". $this->tipo ."','". $this->estado ."')";
        $resp = parent::nonQueryId($query);
        if($resp){
            return $resp;
        }else{
            return 0;
        }

    }
}
