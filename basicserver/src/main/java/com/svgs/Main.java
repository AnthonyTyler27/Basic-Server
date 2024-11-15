package com.svgs;

import static spark.Spark.*;

import java.util.ArrayList;

import com.google.gson.Gson;

public class Main {
    public static ArrayList<Message> messages = new ArrayList<Message>();

    public static void main(String[] args) {
        port(4567);
        ipAddress("0.0.0.0");

        staticFiles.location("/public");
        
        /*
        before((req, res) -> {
            res.header("Access-Control-Allow-Origin", "*");
            res.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            res.header("Access-Control-Allow-Headers", "Content-Type, Authorization");
        });

        options("/*", (req, res) -> {
            String accessControlRequestHeaders = req.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                res.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = req.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                res.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });
        */

        Gson gson = new Gson();

        get("/", (req, res) -> {
            res.redirect("/testProgram.html");
            return null;
        });

        get("/messages", (req, res) -> {
            res.type("application/json");
            return gson.toJson(messages);
        });

        post("/send", (req, res) -> {
            res.type("application/json");
            Message message = gson.fromJson(req.body(), Message.class);
            messages.add(message);
            res.status(201);
            return "";
        });

    }
}