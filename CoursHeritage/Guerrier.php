<?php 

    class Guerrier extends Personnage{



        public function __construct($nom){
            Parent::__construct($nom);
            
        }


        public function renduHTML(){

            
            
            //Parent::renduHTML();
            echo " je m'appel ".$this->_nom." Le Guerrier";
        }


        public function boire(){
        
            echo "je vbois";

        }


    }


?>