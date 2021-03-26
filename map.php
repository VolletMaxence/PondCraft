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
             
            echo "<p><h1>BIENVENUE " .$Joueur1->getPrenom()."</h1></p>";
            echo "<p><h3>Tu est en train de te ballader avec ".$Joueur1->getNomPersonnage()."</h3></p>";
            $map = $Joueur1->getPersonnage()->getMap();
            $map = $map->loadMap($_GET["position"],$_GET["cardinalite"],$Joueur1);

            //chargement des Items
            
            
           

            if(rand(0,3)>1){
                $itemEnplus = new Item($mabase);
                $nbItem = rand(0,2);
                
                for($i=0;$i<$nbItem;$i++){
                    $map->addItem($itemEnplus->createItemAleatoire()); 
                }
            }

            $listItems = $map->getItems();
            if(count($listItems)>0){
                echo '<p>Items Présent : <p><ul class="Item">';
                foreach ( $listItems as  $Item) {
                    ?>
                    <li><a onclick="alert('Pas touche un dev doit venir coder ici le ramassage')"><?php echo $Item->getNom() ?></li>
                    <?php 
                }
                echo '</ul>';
            }
            
            $map->getMapAdjacenteLienHTML();
            echo '<p><a href="index.php" >retour menu choix personnage </a></p>';
            

    }else{
        echo $errorMessage;
    }
    ?>
</body>
</html>