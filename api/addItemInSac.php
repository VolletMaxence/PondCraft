<?php
session_start();
include "../fonction.php"; 

if($access){

    if(isset($_GET["idItem"])){

        //on doit toujours vérifier en bdd la posibilité de l'appel de API
        //iici on va pour un personnage prendre un item de la map et la mettre dans son sac.
       
        $reponse=0;
        $Perso = $Joueur1->getPersonnage();
        $map=$Perso->getMap();
        //une fois que j'ai mes objet je vérifie que le perso est bien sur la map
        
        $idmap = $Perso->getMap()->getId();
       
           //que l'item est bien dans la map
        foreach ($map->getItems()  as $item) {
            if($_GET["idItem"]==$item->getId()){

                //on retire l'item de la map et on la rajoute dans le sac
                $map->removeItemById($_GET["idItem"]);
                $item = new Item($mabase);
                $item->setItemByID($_GET["idItem"]);
                $Perso->addItem($item);
                $reponse=1;
                
            }
        }  
        
    
    
        echo json_encode($reponse);
    }else{
        echo json_encode(0);
    }

}else{
    echo json_encode(0); 
}



?>