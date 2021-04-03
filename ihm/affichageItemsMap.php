<?php

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
?>