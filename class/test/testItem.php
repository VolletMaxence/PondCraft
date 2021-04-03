<?php 
session_start();
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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

        $newItem = new Item($mabase);

        echo "<div><p>Test Creation Item de Soin </p>";
        $newItem= $newItem->createItemSoinConsommable();
        echo "<p>le nom est : ".$newItem->getNom()."</p>";
        echo "<p>le id est : ".$newItem->getId()."</p>";
        echo "<p>la valeur est : ".$newItem->getValeur()."</p>";

        $newItem->getClassRarete();
        if($newItem->getId()>0){
            echo "<p>suppression de l'item ".$newItem->deleteItem($newItem->getId())."</p>";
        }else{
            echo '<div style="color:red">la suppression a echoué car pas id</div>';
        }
       
        echo "</div>";

        echo "<div><p>Test Creation Item aléatoire </p>";
        $newItem= $newItem->createItemAleatoire();
        echo "<p>le nom est : ".$newItem->getNom()."</p>";
        echo "<p>le id est : ".$newItem->getId()."</p>";
        echo "<p>la valeur est : ".$newItem->getValeur()."</p>";
        $newItem->getClassRarete();
        if($newItem->getId()>0){
            echo "<p>suppression de l'item".$newItem->deleteItem($newItem->getId())."</p>";
        }else{
            echo '<div style="color:red">la suppression a echoué car pas id</div>';
        }
       
        echo "</div>";
        
        
        



    }else{
        echo $errorMessage;
    }
    ?>
</body>
</html>