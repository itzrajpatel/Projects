# To run "J.A.R.V.I.S":- streamlit run Jarvis_Version_1.py

import streamlit as st
import requests
import speech_recognition as sr
import pyttsx3
import os

# ===== CONFIG =====
GROQ_API_KEY = ""
MODEL_NAME = "llama3-70b-8192"

# Initialize speech engine
engine = pyttsx3.init(driverName='nsss')
recognizer = sr.Recognizer()

def speak(text):
    engine.say(text)
    engine.runAndWait()

def listen_for_hotword(hotword="hey jarvis"):
    with sr.Microphone() as source:
        print("Listening for hotword...")
        audio = recognizer.listen(source)
        try:
            command = recognizer.recognize_google(audio).lower()
            if hotword in command:
                print(f"Hotword '{hotword}' detected.")
                return True
        except sr.UnknownValueError:
            pass
        except sr.RequestError:
            pass
    return False

def ask_groq(query):
    headers = {
        "Authorization": f"Bearer {GROQ_API_KEY}",
        "Content-Type": "application/json"
    }
    data = {
        "messages": [{"role": "user", "content": query}],
        "model": MODEL_NAME
    }

    try:
        response = requests.post("https://api.groq.com/openai/v1/chat/completions", headers=headers, json=data)
        if response.status_code == 200:
            result = response.json()
            return result["choices"][0]["message"]["content"].strip()
        else:
            return f"Groq API Error: {response.status_code} - {response.text}"
    except Exception as e:
        return f"Error: {str(e)}"

def listen():
    with sr.Microphone() as source:
        st.info("Listening for your question...")
        audio = recognizer.listen(source)
        try:
            query = recognizer.recognize_google(audio)
            st.success(f"You said: {query}")
            return query
        except sr.UnknownValueError:
            speak("Sorry, I didn't catch that.")
            return None
        except sr.RequestError:
            speak("Internet connection error.")
            return None

# ========== Streamlit UI ==========
st.set_page_config(page_title="Jarvis Voice Assistant", page_icon="🎙️")
st.title("🎙️ Jarvis - Your Voice Assistant")

if st.button("🎤 Speak Now"):
    user_query = listen()
    if user_query:
        with st.spinner("Jarvis is thinking..."):
            response = ask_groq(user_query)
        st.markdown("### 🧠 Jarvis says:")
        st.write(response)
        speak(response)
