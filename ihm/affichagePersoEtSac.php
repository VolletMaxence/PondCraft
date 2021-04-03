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
echo '</ul></div>';
echo '</div>';

?>