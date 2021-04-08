
<?php

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
            $Mob->renderHTML();
            ?>
            
        </a>
        </li>
        <?php 
    
    }

    echo '</ul></div>';
}
//affichage des mob déjà attrapé

?>