<?php 

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


?>