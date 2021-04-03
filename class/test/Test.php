<?php 
session_start();
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../css/style.css">
    <link rel="stylesheet" href="../../css/map.css">
    <link rel="stylesheet" href="../../css/perso.css">
    <link rel="stylesheet" href="../../css/item.css">
    <link rel="stylesheet" href="../../css/entite.css">
    <script src="main.js"></script>
    <title>Document</title>
</head>
<body class="bodyAccueil">
    <?php
    //c'est dans fonction que l'on gère les formulaires de Co et les sessions

    include "../../session.php"; 
    if($access){
        $access = $Joueur1->deconnectToi();
    }
    if($access){

        echo "<div> TEST ITEM";
            //include "testUnitaire/testItem.php"; 
        echo "</div>" ;
        echo "<div> TEST ENTITE";
           include "testUnitaire/testEntite.php"; 
        echo "</div>" ;


    }else{
        echo $errorMessage;
    }
    ?>
</body>
</html>

    