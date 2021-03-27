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
             
            $Personnage = $Joueur1->getPersonnage();
            echo "<p><h1>BIENVENUE " .$Joueur1->getPrenom()."</h1></p>";
            echo "<p><h3>Tu est en train de te ballader avec ". $Personnage->getNom()."</h3></p>";
            echo "<p><h3>Ton perso à une Force de ". $Personnage->getAttaque()." et il vaut : ".$Personnage->getValeur()." NFT</h3></p>";
            
            $map = $Personnage->getMap();
            
            if(isset($_GET["position"]) && $Personnage->getVie()>0){
                $map = $map->loadMap($_GET["position"],$_GET["cardinalite"],$Joueur1);
            }else{
                if($Personnage->getVie()==0){
                    $Personnage->resurection();
                    $map = $Personnage->getMap();
                }
                $map = $map->loadMap($map->getPosition(),'nord',$Joueur1);
            }
           
            $Personnage->getBardeVie();
            //affichage des autres joueurs sur la carte

            $listPersos = $map->getAllPersonnages();
            if(count($listPersos)>0){
                echo "<p>Visiblement tu n'est pas seul ici il y a aussi :".'<ul id="Persos" class="Item">';
                foreach ( $listPersos as  $Perso) {
                    if($Perso->getId()!=$Joueur1->getPersonnage()->getId()){
                        ?>
                        <li id="Perso<?php echo $Perso->getId()?>">
                        <a onclick="AttaquerPerso(<?php echo $Perso->getId()?>,0)">
                            <?php  $Perso->renderHTML();?></a>
                        </li>
                        <?php 
                    }
                }
                echo '</ul></p>';
            }

            //affiche les mob;
            $listMob = $map->getAllMobs();
            if(count($listMob)>0){
                echo "<p>Attentions il y a :".'<ul id="Persos" class="Item">';
                foreach ( $listMob as  $Mob) {
                    
                        ?>
                        <li id="Mob<?php echo $Mob->getId()?>">
                        <a onclick="AttaquerPerso(<?php echo $Mob->getId()?>,1)">
                            <?php  $Mob->renderHTML();?></a>
                        </li>
                        <?php 
                    
                }
                echo '</ul></p>';
            }
           

            //AFFICHAGE DES ITEMS DE LA MAP
            $listItems = $map->getItems();
            if(count($listItems)>0){
                echo '<p>Items Présent : <ul class="Item">';
                foreach ( $listItems as  $Item) {
                    ?>
                    <li id="item<?php echo $Item->getId()?>"><a onclick="CallApiAddItemInSac(<?php echo $Item->getId()?>)"><?php echo $Item->getNom() ?></li>
                    <?php 
                }
                echo '</ul></p>';
            }
            
            $map->getMapAdjacenteLienHTML();
            $map->getImageCssBack();


            //AFFICHAGE DES ITEMS DU SAC
            echo "<p>Voici le contenu de la bedasse de ".$Joueur1->getNomPersonnage()." </p>";
            $listItems = $Joueur1->getPersonnage()->getItems();
            echo '<p><ul id="Sac" class="Item">';
            if(count($listItems)>0){
                foreach ( $listItems as  $Item) {
                    ?>
                    <li id="itemSac<?php echo $Item->getId()?>"><a onclick="DetruireItem(<?php echo $Item->getId()?>)"><?php echo $Item->getNom() ?></li>
                    <?php 
                }
            }
            echo '</ul></p>';
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
    if(data[0]!=0 && data[1]==1){
        var li = document.getElementById("item"+idItem)
        var liSac = li;
        if (li!='undefine'){
            li.remove();
        }
        var ul = document.getElementById("Sac")
        if (ul!='undefine'){
            ul.appendChild(liSac);
        }
    } else{
        
        alert("vous avez pas réussi à le piquer "+data[1]);
    }  

    }) .catch(function(error) {
    // This is where you run code if the server returns any errors
    console.log(error); });
}

function DetruireItem(idItem){
    alert("bientot tu pourras en faire un truc de cet item si les dev se bouge !");
}
function AttaquerPerso(idPerso,type){
    getVie(idPerso,type)
}
</script>
</html>