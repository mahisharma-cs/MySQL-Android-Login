<?php

//Read all he comments carefully
// Read README file also.



//This file is contain the information of database.
	require "../config.php";
	
//We are fetchng these from Android application
	$username = $_POST["username"];
	$password = $_POST["password"];
	
//Declare an array to display JSON objects
	$response = array();
	
//Select ID of particular user
	$sql = "SELECT id FROM users WHERE username ='".$username."' ";
	$stmt = mysqli_prepare($conn,$sql);
	mysqli_stmt_execute($stmt);
	mysqli_stmt_store_result($stmt);
	if(mysqli_stmt_num_rows($stmt) == 1)
	{
//Store ID of that user
		mysqli_stmt_bind_result($stmt,$userid);
		mysqli_stmt_fetch($stmt);

////Select password of particular user with that id
		$sqluser = "SELECT password FROM users WHERE id =".$id." ";
		$stmtuser = mysqli_prepare($conn,$sqluser);
		mysqli_stmt_execute($stmtuser);
		mysqli_stmt_store_result($stmtuser);
		mysqli_stmt_bind_result($stmtuser,$hashed_password);
		mysqli_stmt_fetch($stmtuser);

//Apply verify password method of your password is encoded with hashed password default
		if(password_verify($password,$hashed_password))
		{
//If password is verified... then the variable name "status" is "ok"...
			$status = "ok";
		}
		else
		{
//If password is not verified... then the variable name "status" is "failed"...
			$status = "falied";
		}
	}
	else
	{
//If user not exist... then the variable name "status" is "notexist"...
		$status = "notexist";
	}
	
//Now we are adding the whatever variable value into created array with the index name - status.(you can give any name)
	$response["status"] = $status;

//It is importnat to print the array in JSON ENCODE format.
	echo json_encode($response);

?>