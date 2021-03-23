<?php
$mabase = null;
try{
            $user = "lapro_site";
            $pass = "TDataSource1234";

            $mabase = new PDO('mysql:host=mysql-lapro.alwaysdata.net;dbname=lapro_virus', $user, $pass);
            

}catch(Exception $e){
    echo $e->getMessage();
}
?>