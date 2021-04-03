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
            echo '<div class="reglement"><p>Il faut créer un personnage d\'abord.</p>';
            echo '<p><a  href="index.php" >Retour à l\'origine du tout </a></p></div>';
        }else{
           
            echo '<div class="reglement">';
            $personnage->getChoixPersonnage($Joueur1->getId());
            $map = $personnage->getMap();
            $tabDirection = $map->getMapAdjacenteLienHTML('nord',$Joueur1);
            
            
            echo $tabDirection['nord'];
            echo '<h1>Bienvenue '.$Joueur1->getPrenom()."</h1>";
            echo "Tu as décidé de combattre avec ".$Joueur1->getNomPersonnage(). ", il a une fortune de ".$personnage->getValeur()." (NFT)";
            
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

            echo "<p>Ton combattant est sur la position : ".$map->getNom().'</p>';
            echo "<p><h4>Tu peux maintenant ramasser des conneries par terre.</h4></p>";
            echo "<p><h4>Si tu en trouves qui sont parfaitement identiques, elles prennent de la valeur 😄 !</h4></p>";
            echo "<p><h3>But du jeu : Capture le \"Super Jedi Légendaire\"</h3></p>";
            echo '<div class="tableaChass"> <div class="titreMonster">Voici tes monstres capturés :</div>';
            $MysMob = new Mob($mabase);
            foreach ($Joueur1->getAllMyMobIds() as $mob) {
                echo '<div class="monster">';
                $MysMob->setMobById($mob);
                echo $MysMob->generateImage();
                $MysMob->renderHTML();
                echo "</div>";
            }?>

                <div class="titreMonster">Seul un certain pouvoir peut protéger tes monstres d'une capture...</div>
            </div><p><a href="index.php" >Créer un autre personnage.</a></p></div>
            </div>
            <?php
            $tabDirection = $map->getMapAdjacenteLienHTML('nord',$Joueur1);
        }

    }else{
        echo $errorMessage;
    }
    ?>
</body>
</html>