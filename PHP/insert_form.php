<?php


   // require_once('config.inc.php');
	
	$servername = "141.45.92.216";
$username = "kimyadb";
$password = "kimya123";
$dbname = "chemscandb";

	 $anlage = $_POST['Anlage_spinner'];
	 $einheit = $_POST['Einheit_spinner'];
	 $wirkdauer = $_POST['Wirkdauer_spinner'];

	 
	 
	/*  foreach( $_POST as $stuff ) {
    if( is_array( $stuff ) ) {
        foreach( $stuff as $thing ) {
            echo $thing;
        }
    } else {
        echo $stuff;
    }
} */
   
 /*  
    $sql="INSERT INTO `A` VALUES (NULL, 'test element 1')";
    if (mysqli_query($connection,$sql)) {
       echo "Values have been inserted successfully";
    } */
	
	
	   $con=mysqli_connect($servername,$username,$password,$dbname);
   $sql="INSERT INTO `A` VALUES (NULL, '$anlage', '$einheit', '$wirkdauer')";
   if (mysqli_query($con,$sql)) {
      echo "Values have been inserted successfully";
   }
   
?>