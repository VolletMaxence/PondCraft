
<?php

        $newItem = new Item($mabase);



        echo "<div><p>Test 1 Creation Item de Soin </p>";
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

        echo "<div><p>Test 2 Creation Item aléatoire </p>";
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



        echo "<div><p>Test 3 100 Item aléatoire </p>";
        $listItems = array();
        for($i=0;$i<100;$i++){
            array_push($listItems,$newItem->createItemAleatoire());
        }
        
        if(count($listItems)>0){
            echo '<div class="left">Items Présent : <div class="divRarete"> Commun - Rare</div><ul class="Item">';
            foreach ( $listItems as  $Item) {
                ?>
                <li id="item<?php echo $Item->getId()?>" style="<?php echo $Item->getClassRarete()?>">
                    <a onclick="CallApiAddItemInSac(<?php echo $Item->getId()?>)">
                        <?php echo $Item->getNom() ?></a>
                </li>
                <?php 
                $Item->deleteItem($Item->getId());
            }
            echo '</ul></div>';
        }
       
        echo "</div>";

?>
        
        
        


