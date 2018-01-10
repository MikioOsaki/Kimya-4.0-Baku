<?php
$servername = "141.45.92.216";
$username = "kimyadb";
$password = "kimya123";
$dbname = "chemscandb";

try {
    	$connection = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
    	$connection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
         //mysqli_set_charset($connection, 'uft8');
    }
catch(PDOException $e)
    {
    	die("OOPs something went wrong");
    }

?>