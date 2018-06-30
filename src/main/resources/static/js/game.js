"use strict";
var winningPattern = [];
var userPattern = [];
var currentRound = 0;
var gameActive = false;
var count = 5;
var counter;
function greenAdd() {
    winningPattern.push("green");
}
function redAdd() {
    winningPattern.push("red");
}
function yellowAdd() {
    winningPattern.push("yellow");
}
function blueAdd() {
    winningPattern.push("blue");
}
function greenShow() {
    $(".green").addClass("dark");
    setTimeout(function () {
        $(".green").removeClass("dark");
    }, 130);
}
function redShow() {
    $(".red").addClass("dark");
    setTimeout(function () {
        $(".red").removeClass("dark");
    }, 130);
}
function yellowShow() {
    $(".yellow").addClass("dark");
    setTimeout(function () {
        $(".yellow").removeClass("dark");
    }, 130);
}
function blueShow() {
    $(".blue").addClass("dark");
    setTimeout(function () {
        $(".blue").removeClass("dark");
    }, 130);
}
function displayCurrent(i) {
    switch (i) {
        case "green":
            greenShow();
            break;
        case "red":
            redShow();
            break;
        case "yellow":
            yellowShow();
            break;
        case "blue":
            blueShow();
            break;
    }
}
function switchFunc(i) {
    switch (i) {
        case 0:
            greenAdd();
            break;
        case 1:
            redAdd();
            break;
        case 2:
            yellowAdd();
            break;
        case 3:
            blueAdd();
            break;
    }
}
// counts game down before beginning
function countdown() {
    if (count <= 0) {
        clearInterval(counter);
    }else {
        count = count-1;
        $(".timer").text(count);
    }
}
// function to play the game
function startGame() {
    currentRound++;
    $(".round").html("<h3>Round: " + currentRound + "</h3>");
    setTimeout(function () {
        var random = Math.floor(Math.random() * 4);
        var colors = ["green","red","yellow","blue"];
        displayCurrent(colors[random]);
        switchFunc(random);
        console.log(winningPattern);
    }, 2500)}
// function that fires when game ends
function gameOver() {
    userPattern = [];
    winningPattern = [];
    count = 5;
    $(".timer").text(count);
    gameActive = false;
    $(".gameover").removeClass("hidden");
    currentRound = 0;
    // $(".round").html("<h3>Round: " + currentRound + "</h3>");
}
function nextRound(array) {
    currentRound++;
    $(".round").html("<h3>Round: " + currentRound + "</h3>");
    setTimeout(function () {
        userPattern = [];
        console.log(array);
        var counter = 0;
        var show = setInterval (function () {
            displayCurrent(array[counter]);
            counter++;
            if(counter === array.length) {
                clearInterval(show)
            }
        }, 750);
        var random = Math.floor(Math.random() * 4);
        switchFunc(random);
    }, 500)}
// start button to begin game
$(".start").click(function () {
    counter = setInterval(countdown, 500);
    $(".gameover").addClass("hidden");
    userPattern = [];
    winningPattern = [];
    gameActive = true;
    currentRound = 0;
    startGame();
});
$(".semi-circle").click(function () {
    $(this).addClass("dark");
    setTimeout(function () {
        $(".semi-circle").removeClass("dark");
    }, 130);
    if (gameActive === true) {
        userPattern.push($(this).prop('id'));
        console.log(userPattern);
    } else {
        gameOver()
    }
    if (gameActive === true && userPattern.length === winningPattern.length) {
        if (userPattern.toString() === winningPattern.toString()) {
            console.log("nice!");
            nextRound(winningPattern);
        } else {
            gameOver();
            console.log("nope :(")
        }
    }
});