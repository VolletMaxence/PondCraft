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
    <title>Combat</title>
</head>
<body>
    
    <?php
    include "fonction.php"; 

    $access = $Joueur1->deconnectToi();
    if($access){
        

        $personnage = $Joueur1->getPersonnage();
        $map = $personnage->getMap();

        echo "<div><h1>BIENVENUE " .$Joueur1->getPrenom()."</h1>";
        echo "TU AS CHOISIE COMBATRE AVEC ".$Joueur1->getNomPersonnage(). " il a une fortune de ".$personnage->getValeur()." (NFT)";
        echo "<p>Ton combatant est sur la position : ".$map->getNom().'</p>';
        echo "<p><h4>Tu peux maintenant ramasser des conneries par terre</h4></p>";
        echo "<p><h4>Si tu en trouve des parfaitements identiques elle prennent de la valeur :D</h4></p>";

        echo '<p><a href="index.php" >Changer de personnage</a></p>';

        $map->getMapAdjacenteLienHTML();

    }else{
        echo $errorMessage;
    }
    ?>
</body>
</html>