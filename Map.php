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

    if($access){
        
        //gestion accès map:
        if(isset($_GET["position"]) && isset($_GET["cardinalite"]) ){
           

            echo "BIENVENUE " .$Joueur1->getPrenom();
            echo "TU COMBAT AVEC ".$Joueur1->getNomPersonnage();
            $map = $Joueur1->getPersonnage()->getMap();
            echo "<p> il vient : ". $map->getNom()."</p>";

            if($_GET["position"]==="Generate"){
                //la cardinalité permet de lui dire d'ou on vient
                $map = $map->Create($Joueur1->getPersonnage()->getMap(),$_GET["cardinalite"],$Joueur1->getId());
                if(!is_null($map)){
                    
                    echo "<p>tu viens de découvrir une nouvelle  position : ". $map->getNom()." </p>";
                   

                    //puis on déplace le joueur
                    $Joueur1->getPersonnage()->ChangeMap($map);

                }else{
                    echo "<p>tu t'es perdu mon petit pot da découverte tombe à l'eau";
                }
                

            }else if ($_GET["position"]>=0) {
                //récupération de la map est atttribution au combatant
                
                $map->setMapByPosition($_GET["position"]);
                echo "<p>tu es sur la position : ". $map->getNom()." </p>";
                $Joueur1->getPersonnage()->ChangeMap($map);
                
            }else{
                echo "Tu es en terre  Incconu revient vite là ou tu étais";
            }

        }else{
            echo "Tu es en terre  Incconu revient vite là ou tu étais";
        }

        echo '<p><a href="index.php" >retour menu choix personnage</a></p>';
      
        $Joueur1->getPersonnage()->getMap()->getMapAdjacenteLienHTML();

    }else{
        echo $errorMessage;
    }
    ?>
</body>
</html>