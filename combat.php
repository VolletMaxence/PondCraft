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
<body class="bodyAccueil">
    
    <?php
    include "fonction.php"; 

    if($access){
        $access = $Joueur1->deconnectToi();
    }
    if($access){
        

        $personnage = $Joueur1->getPersonnage();
        if(is_null($personnage->getId())){
            echo '<div class="reglement"><p>il faut creer un personnage avant</p>';
            echo '<p><a  href="index.php" >retour à l\'origine de tout </a></p></div>';
        }else{
            echo '<div class="reglement"><h1>Bienvenu '.$Joueur1->getPrenom()."</h1>";
            $personnage->getChoixPersonnage($Joueur1->getId());
            $map = $personnage->getMap();

           
            echo "Tu as choisie de combatre avec ".$Joueur1->getNomPersonnage(). " il a une fortune de ".$personnage->getValeur()." (NFT)";
            echo '<div class="avatar">';
                $personnage->renderHTML();

                //AFFICHAGE DES ITEMS DU SAC
                $listItems = $Joueur1->getPersonnage()->getItems();
                echo '<div class="divSac">Sacoche<ul id="Sac" class="Sac">';
                if(count($listItems)>0){
                    foreach ( $listItems as  $Item) {
                        ?>
                        <li id="itemSac<?php echo $Item->getId()?>"><a onclick="useItem(<?php echo $Item->getId()?>)"><?php echo $Item->getNom() ?></li>
                        <?php 
                    }
                }
                echo '</ul></div>';
                
            echo "<p>Ton combatant est sur la position : ".$map->getNom().'</p>';
            echo "<p><h4>Tu peux maintenant ramasser des conneries par terre</h4></p>";
            echo "<p><h4>Si tu en trouve des parfaitements identiques elle prennent de la valeur :D</h4></p>";
            echo "<p><h3>Trouve est tue le ''Super Sayan Legendaire''</h3></p>";
            echo '<p><a href="index.php" >Creer un autre personnage</a></p></div>';
            echo '</div>';
            $map->getMapAdjacenteLienHTML('nord');
        }

    }else{
        echo $errorMessage;
    }
    ?>
</body>
</html>