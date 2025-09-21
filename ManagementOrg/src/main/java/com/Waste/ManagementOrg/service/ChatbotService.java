package com.Waste.ManagementOrg.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ChatbotService {

        private static final Map<String, String> faq = new HashMap<>();

        static {
            faq.put("What kind of garbage can I actually send to you?" , "We'd love your waste! We mainly focus on materials that can be upcycled (transformed without breaking them down). This includes things like: Old denim/fabric, plastic bottles/caps, wine corks, and paper/cardboard. Check our Material Donation Guide for a full list and instructions!");
            faq.put("How do I know this product is really recycled and safe?", " Great question! Every NISARG product has a unique \"Waste-Chain\" QR code. Scan it to see its journey: the type of waste used, where it was collected (e.g., a local community drive), the cleaning/sanitization process, and the artisan who made it. Full transparency guaranteed! ");
            faq.put("How much waste has NISARG saved from landfills this year?" , "We're making a big impact thanks to our community! So far this year, we've diverted [Insert Real-Time Number, e.g., 15,482 kg] of waste from landfills. You can see our live impact counter on the homepage, and your purchases directly contribute to that number!");
            faq.put("what is waste management", "Waste management involves collection, transport, and disposal of garbage.");
            faq.put("how to dispose e-waste", "You can dispose e-waste at the nearest recycling center or collection booth.");
            faq.put("complaint", "You can raise a complaint in the complaint section.");
            faq.put("schedule", "Garbage collection is twice a week. Please check your zone schedule.");
        }

        public String getResponse(String query) {
            query = query.toLowerCase();

            for (String key : faq.keySet()) {
                if (query.contains(key)) {
                    return faq.get(key);
                }
            }

            return "Sorry, I don’t have an answer. Please contact your local office.";
        }
    }

