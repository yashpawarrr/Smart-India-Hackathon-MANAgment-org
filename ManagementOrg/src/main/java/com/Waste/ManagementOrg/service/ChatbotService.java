package com.Waste.ManagementOrg.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ChatbotService {

        private static final Map<String, String> faq = new HashMap<>();

        static {
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

