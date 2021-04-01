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
    <div class="centragePrincipal">
    <?php
    include "fonction.php"; 
    
    if($access){
        $access = $Joueur1->deconnectToi();
    }

    if($access){
        
        //gestion accès map:
             
            $Personnage = $Joueur1->getPersonnage();
            if(is_null($Personnage->getId())){
                echo '<p>il faut creer un personnage avant</p>';
                echo '<p><a  href="index.php" >retour à l\'origine de tout </a></p>';
            }else{
                echo '<p><a  href="index.php" >retour à l\'origine de tout </a></p>';
                echo "<div>";
                if(isset($_GET["position"])&& $_GET["position"]==='Generate'){
                    //TODO lol la fleme de faire la negation de ce if
                }else{
                    echo "tu peux appeler un autre Personnage ";
                    $Personnage->getChoixPersonnage($Joueur1->getId());

                    $Joueur1->setPersonnage($Personnage);
                }


                echo "</div>";

                echo '<div class="avatar">';
                $Personnage->renderHTML();

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
                echo '</div>';

                
                //AFFICHAGE DE LA MAP

                echo '<div class="lamap">';
                $map = $Personnage->getMap();

                //gestion de la téléportation
                $TelportationPositionDepart = $map->getPosition();

                $cardinalite = '';
                if(isset($_GET["cardinalite"])){
                    $cardinalite = $_GET["cardinalite"];
                }

                if($map->LogVisiteMap($Personnage)){ //control de spam.

                    if(isset($_GET["position"]) && $Personnage->getVie()>0){
                        $map = $map->loadMap($_GET["position"],$cardinalite,$Joueur1);
                    }else{
                        if($Personnage->getVie()==0){
                            $Personnage->resurection();
                            $map = $Personnage->getMap();
                        }
                        $map = $map->loadMap($map->getPosition(),'nord',$Joueur1);
                    }
                    
                     //puis on déplace le joueur
                    $Joueur1->getPersonnage()->ChangeMap($map);
                }
                $Joueur1->getVisitesHTML(6);
            
                //affichage des autres joueurs sur la carte

                $listPersos = $map->getAllPersonnages();
                if(count($listPersos)>1){
                    echo '<div class="left">Visiblement tu n\'est pas seul ici il y a aussi :'.'<ul id="ulPersos" class="Persos">';
                    $PersoJoeuur = $Joueur1->getPersonnage();
                    foreach ( $listPersos as  $Perso) {
                        if($Perso->getId()!=$PersoJoeuur->getId()){
                            ?>
                            <li id="Perso<?php echo $Perso->getId()?>">
                            <a onclick="AttaquerPerso(<?php echo $Perso->getId()?>,0)">
                                <?php  $Perso->renderHTML();?></a>
                            </li>
                            <?php 
                        }
                    }
                    echo '</ul></div>';
                }

                //affiche les mob enemie et capturé;
                $listMob = $map->getAllMobs();
                if(count($listMob)>0){
                    echo '<div class="left">'.'<ul id="ulMob" class="Mob">';
                    $Mob = new Mob($mabase);

                    //affichage des Mob Enemi
                    $mobContre = $map->getAllMobContre($Joueur1);
                    if(count($mobContre)>0){
                        echo "<div>Tu es bloqué car il y a des mobs<div>";
                    }
                    foreach (  $mobContre as  $MobID) {
                        $Mob->setMobById($MobID);
                        ?>
                        <li id="Mob<?php echo $Mob->getId()?>" class="adverse">
                        <a onclick="AttaquerPerso(<?php echo $Mob->getId()?>,1)">
                            <?php  
                            echo $Mob->generateImage();
                            $Mob->renderHTML();
                            ?>
                            
                        </a>
                        </li>
                        <?php 
                        
                    }

                    //affichage des Mob Capturés
                    foreach ( $map->getAllMobCapture($Joueur1) as  $MobID) {
                        $Mob->setMobById($MobID);
                        ?>
                        <li id="Mob<?php echo $Mob->getId()?>" class="Captured">
                        <a onclick="SoinMob(<?php echo $Mob->getId()?>,1)">
                            <?php  
                            echo $Mob->generateImage();
                            $Mob->renderHTML();
                            ?>
                            
                        </a>
                        </li>
                        <?php 
                    
                }
  
                    echo '</ul></div>';
                }
                //affichage des mob déjà attrapé

            

                //AFFICHAGE DES ITEMS DE LA MAP
                $listItems = $map->getItems();
                if(count($listItems)>0){
                    echo '<div class="left">Items Présent : <div class="divRarete"> Commun - Rare</div><ul class="Item">';
                    foreach ( $listItems as  $Item) {
                        ?>
                        <li id="item<?php echo $Item->getId()?>" style="<?php echo $Item->getClassRarete()?>">
                            <a onclick="CallApiAddItemInSac(<?php echo $Item->getId()?>)">
                                <?php echo $Item->getNom() ?></a>
                        </li>
                        <?php 
                    }
                    echo '</ul></div>';
                }
                

                echo '</div>'; //DIV DE LA MAP


                


                $map->getMapAdjacenteLienHTML($cardinalite,$Joueur1);

                $map->getImageCssBack();

            ?>
                <div class="basdepage">
                </div>
                <?php
            }
            
    }else{
        echo $errorMessage;
    }
    ?>
    </div><!--fin centragePrincipal"-->
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

        
        alert("vous avez pas réussi à le piquer "+data[2]);
    }  

    }) .catch(function(error) {
    // This is where you run code if the server returns any errors
    console.log(error); });
}


function AttaquerPerso(idPerso,type){
    attaquer(idPerso,type)
}
</script>
</html>