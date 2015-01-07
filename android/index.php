<?php
/**
 * File to handle all API requests
 * Accepts GET and POST
 * 
 * Each request will be identified by TAG
 * Response will be JSON data
 
  /**
 * check for POST request 
 */
if (isset($_POST['tag']) && $_POST['tag'] != '') {
    // get tag
    $tag = $_POST['tag'];
 
    // include db handler
    require_once 'include/DB_Functions.php';
    $db = new DB_Functions();
 
    // response Array
    $response = array("tag" => $tag, "success" => 0, "error" => 0);
 
    // check for tag type
    if ($tag == 'student_details') {
        // Request type is chemethodck student_details
		$enroll = $_POST['enrollment_num'];
        //$email = $_POST['email'];
        $password = $_POST['password'];
 
        // check for user
        $user = $db->getUserByEmailAndPassword($enroll, $password);
        if ($user != false) {
            // user found
            // echo json with success = 1
            $response["success"] = 1;
            //$response["uid"] = $user["unique_id"];
			$response["user"]["enrollment_num"] = $user["enrollment_num"];
            $response["user"]["name"] = $user["name"];
            $response["user"]["email"] = $user["email"];
            $response["user"]["contact"] = $user["contact"];
            $response["user"]["branch"] = $user["branch"];
			$response["user"]["year"] = $user["year"];
            echo json_encode($response);
        } else {
            // user not found
            // echo json with error = 1
            $response["error"] = 1;
            $response["error_msg"] = "Incorrect enrollment number or password!";
            echo json_encode($response);
        }
    } else if ($tag == 'register') {
        // Request type is Register new user
		$enroll = $_POST['enrollment_num'];
        $name = $_POST['name'];
        $email = $_POST['email'];
        $password = $_POST['password'];
		$contact = $_POST['contact'];
		$branch = $_POST['branch'];
		$year = $_POST['year'];
 
        // check if user is already existed
        if ($db->isUserExisted($enroll)) {
            // user is already existed - error response
            $response["error"] = 2;
            $response["error_msg"] = "User already existed";
            echo json_encode($response);
        } else {
            // store user
            $user = $db->storeUser($enroll, $name, $email, $password, $contact, $branch, $year);
            if ($user) {
                // user stored successfully
                $response["success"] = 1;
                //$response["uid"] = $user["unique_id"];
                $response["user"]["enrollment_num"] = $user["enrollment_num"];
				$response["user"]["name"] = $user["name"];
                $response["user"]["email"] = $user["email"];
                $response["user"]["contact"] = $user["contact"];
                $response["user"]["branch"] = $user["branch"];
				$response["user"]["year"] = $user["year"];
                echo json_encode($response);
            } else {
                // user failed to store
                $response["error"] = 1;
                $response["error_msg"] = "Error occured in Registartion";
                echo json_encode($response);
            }
        }
    } else {
        echo "Invalid Request";
    }
} else {
    echo "Access Denied";
}
?>