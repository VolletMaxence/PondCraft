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
        
        echo "BIENVENUE " .$Joueur1->getPrenom();
        echo "TU COMBAT AVEC ".$Joueur1->getNomPersonnage();
        echo "<p>Ton combatant est sur la position : ".$Joueur1->getPersonnage()->getMap()->getNom().'</p>';

        echo '<p><a href="index.php" >retour menu</a></p>';

        $Joueur1->getPersonnage()->getMap()->getMapAdjacenteLienHTML();

    }else{
        echo $errorMessage;
    }
    ?>
</body>
</html>