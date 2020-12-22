<?php

    $con = mysqli_connect('localhost', 'root', '', 'unitydatabase');

    // Kiem tra ket noi:
    if ( mysqli_connect_errno() ) {

        echo "#1: Connection error"; // #1: ket noi loi :<
        exit();

    }

    $username = $_POST["name"];
    $password = $_POST["password"];

    // Kiem tra username da duoc su dung:
    $namecheckquery = "SELECT username FROM players WHERE username ='" . $username . "';";

    $namecheck = mysqli_query($con, $namecheckquery) or die("2: Name check failed"); // #2: kiem tra teen loi :<

    if ( mysqli_num_rows($namecheck) > 0 ) {

        echo "3: Name already exists"; // #3: Ten da duoc dung
        exit();

    }

    // Them user vao bang:
    $salt = "\$5\$rounds=5000\$" . "steamedhams" . $username . "\$";
    $hash = crypt($password, $salt);
    $insertuserquery = "INSERT INTO players (username, hash, salt) VALUES ('" . $username . "', '" . $hash . "', '" . $salt . "');";
    mysqli_query($con, $insertuserquery) or die ("4: Insert user failed");

    echo "0";

?>