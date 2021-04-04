<?php

echo "</div>";
echo '<div class="avatar">';
$Personnage->renderHTML();

//AFFICHAGE DES ITEMS DU SAC
$listItems = $Joueur1->getPersonnage()->getItems();
echo '<div class="divSac">Sacoche<ul id="Sac" class="Sac">';
if(count($listItems)>0){
    foreach ( $listItems as  $Item) {
        ?>
        <li id="itemSac<?php echo $Item->getId()?>"><a onclick="useItem(<?php echo $Item->getId()?>)"><?php echo $Item->getNom() ?></a></li>
        <?php 
    }
}

//Equipement
$listEquipements = $Joueur1->getPersonnage()->getEquipements();
if(count($listEquipements)>0){
    foreach ( $listEquipements as  $Equipement) {
        ?>
        <li id="equipementSac<?php echo $Equipement->getId()?>"><a onclick="useEquipement(<?php echo $Equipement->getId()?>)"><?php echo $Equipement->getNom() ?></a></li>
        <?php 
    }
}

echo '</ul>
</div>';
echo '</div>';

?>