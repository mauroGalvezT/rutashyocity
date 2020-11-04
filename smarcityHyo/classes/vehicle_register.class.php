<?php
require_once 'database/conexion.php';
require_once 'respuestas.class.php';

class vehicle_register extends conexion
{
    private $table_name = "tbl_vehiculo";
    private $vehiculo;
    private $placa;
    private $color;
    private $modelo;
    private $marca;
    private $idTipoVehiculo;
    private $idConductor;


    public function postMetod($json)
    {
        $_respuestas = new respuestas;
        $datos = json_decode($json, true);
        if (!isset($datos['token'])) {
            return $_respuestas->error_401();

        } else {
            $this->token = $datos['token'];
            $arrayToken = $this->buscarToken();

            if ($arrayToken) {
                if (!isset($datos['vehiculo']) || !isset($datos['placa']) || !isset($datos['color']) || !isset($datos['modelo']) || !isset($datos['marca']) || !isset($datos['idTipoVehiculo'])) {
                    return $_respuestas->error_400();
                } else {

                    $this->vehiculo = $datos['vehiculo'];
                    $this->placa = $datos['placa'];
                    $this->color = $datos['color'];
                    $this->modelo = $datos['modelo'];
                    $this->marca = $datos['marca'];
                    $this->idTipoVehiculo = $datos['idTipoVehiculo'];
                    $this->idConductor = $arrayToken[0]["ID_Conductor"];

                    $resp = $this->registrarVehiculo();

                    if ($resp) {
                        $signup = array(
                            "status" => true,
                            "message" => "Registro correto"
                        );
                        return $signup;
                    } else {
                        return $_respuestas->error_500();
                    }
                }
            } else {
                return $_respuestas->error_500();
            }
        }
    }
    private function buscarToken()
    {
        $query = "SELECT  TokenId,ID_Conductor,Estado from driver_token WHERE Token = '" . $this->token . "' AND Estado = 'Activo'";
        $resp = parent::obtenerDatos($query);
        if ($resp) {
            return $resp;
        } else {
            return 0;
        }
    }
    private function registrarVehiculo()
    {
        $query = "INSERT INTO " . $this->table_name . "(VEH_Placa, VEH_Color,VEH_Modelo, VEH_Marca,ID_Tipo_Vehiculo,ID_Conductor) values ('" . $this->placa . "','" . $this->color . "','" . $this->modelo . "','" . $this->marca . "','" . $this->idTipoVehiculo . "','" . $this->idConductor . "')";


        $resp = parent::nonQueryId($query);
        if ($resp) {
            return $resp;
        } else {
            return 0;
        }
    }
}
