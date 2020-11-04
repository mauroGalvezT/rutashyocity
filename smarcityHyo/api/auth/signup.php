<?php
require_once '../../classes/respuestas.class.php';
require_once '../../classes/signup.class.php';

$_respuestas = new respuestas;
$_signup = new signup;

if($_SERVER['REQUEST_METHOD'] == "POST"){

        //recibimos los datos enviados
        $postBody = file_get_contents("php://input");
        //enviamos los datos al manejador
        $datosArray = $_signup->post($postBody);
        //delvovemos una respuesta 
         header('Content-Type: application/json');
         if(isset($datosArray["result"]["error_id"])){
             $responseCode = $datosArray["result"]["error_id"];
             http_response_code($responseCode);
         }else{
             http_response_code(200);
         }
         echo json_encode($datosArray);
}else{
    header('Content-Type: application/json');
    $datosArray = $_respuestas->error_405();
    echo json_encode($datosArray);
}