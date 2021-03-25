<?php 
session_start();

?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <script src="main.js"></script>
    <title>Document</title>
</head>
<body>
    
    <?php
    //c'est dans fonction que l'on gère les formulaires de Co et les sessions
    include "fonction.php"; 

    if($access){
        
        echo "BIENVENUE sur MON SITE ".$Joueur1->getPrenom();
        echo '<a href="combat.php">vient combatre</a>';

    }else{
        echo $errorMessage;
    }
    ?>
</body>
</html>
