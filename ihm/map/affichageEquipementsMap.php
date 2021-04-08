<?php

$listEquipements = $map->getEquipements();
if(count($listEquipements)>0){
    echo '<div class="left">Equipements Présent : <div class="divRarete"> Commun - Rare</div><ul class="Equipement">';
    foreach ( $listEquipements as  $Equipement) {
        ?>
        <li id="equipement<?php echo $Equipement->getId()?>" style="<?php echo $Equipement->getClassRarete()?>">
            <a onclick="CallApiAddEquipementInSac(<?php echo $Equipement->getId()?>)">
                <?php echo $Equipement->getNom() ?></a>
        </li>
        <?php 
    }
    echo '</ul></div>';
}
?>