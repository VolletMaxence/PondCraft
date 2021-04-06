<?php 

    class Personnage{


        protected $_nom;

        public function __construct($nom){
            $this->_nom = $nom;
        }


        public function renduHTML(){
            ?>
            <div class="Personnage">
                <p>je suis <?php echo $this->_nom ?></p>
            </div>

            <?php
        }

    }


?>