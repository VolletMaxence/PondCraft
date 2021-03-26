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
        
        //gestion accès map:
             
            echo "<p><h1>BIENVENUE " .$Joueur1->getPrenom()."</h1></p>";
            echo "<p><h3>Tu est en train de te ballader avec ".$Joueur1->getNomPersonnage()."</h3></p>";
            
            $map = $Joueur1->getPersonnage()->getMap();
            $map = $map->loadMap($_GET["position"],$_GET["cardinalite"],$Joueur1);

            //chargement des Items

            if(rand(0,1)>1){
                $itemEnplus = new Item($mabase);
                $nbItem = rand(0,2);
                
                for($i=0;$i<$nbItem;$i++){
                    $map->addItem($itemEnplus->createItemAleatoire()); 
                }
            }

            //AFFICHAGE DES ITEMS DE LA MAP
            $listItems = $map->getItems();
            if(count($listItems)>0){
                echo '<p>Items Présent : <p><ul class="Item">';
                foreach ( $listItems as  $Item) {
                    ?>
                    <li id="item<?php echo $Item->getId()?>"><a onclick="CallApiAddItemInSac(<?php echo $Item->getId()?>)"><?php echo $Item->getNom() ?></li>
                    <?php 
                }
                echo '</ul></p>';
            }
            
            $map->getMapAdjacenteLienHTML();


            //AFFICHAGE DES ITEMS DU SAC
            echo "<p>Voici le contenu de la bedasse de ".$Joueur1->getNomPersonnage()." </p>";
            $listItems = $Joueur1->getPersonnage()->getItems();
            if(count($listItems)>0){
                echo '<p><ul id="Sac" class="Item">';
                foreach ( $listItems as  $Item) {
                    ?>
                    <li id="itemSac<?php echo $Item->getId()?>"><a onclick="DetruireItem(<?php echo $Item->getId()?>)"><?php echo $Item->getNom() ?></li>
                    <?php 
                }
                echo '</ul></p>';
            }

            echo '<p><a href="index.php" >retour menu choix personnage </a></p>';
            

    }else{
        echo $errorMessage;
    }
    ?>
</body>
<script>
function CallApiAddItemInSac(idItem){
    fetch('api/addItemInSac.php?idItem='+idItem).then((resp) => resp.json()) .then(function(data) {
    // data est la réponse http de notre API.
    console.log(data); 
    if(data==1){
        var li = document.getElementById("item"+idItem)
        var liSac = li;
        if (li!='undefine'){
            li.remove();
        }
        var ul = document.getElementById("Sac")
        if (ul!='undefine'){
          
            ul.appendChild(liSac);
            
        }



    }   
    
    }) .catch(function(error) {
    // This is where you run code if the server returns any errors
    console.log(error); });
}

function DetruireItem(idItem){
    alert("bientot tu pourras en faire un truc de cet item si les dev se bouge !");
}
</script>
</html>