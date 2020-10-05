 <?php

date_default_timezone_set('Asia/Kolkata'); //define local time
$logts = date('Y-m-d H:i:s');

$servername = "hosting.net";
$username = "dbtest-3139d5a5";
$password = "";
$dbname = "dbtest";

try {
  $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
  // set the PDO error mode to exception
  $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
// echo "Connected successfully";
} catch(PDOException $e) {
  echo "Connection failed: " . $e->getMessage();
}

?>
