# 🚀 Smart PDF Question Extractor - Backend

## 📌 Overview
This is the backend service for extracting questions from PDF files.  
It uses Spring Boot and integrates with Ollama (DeepSeek model) for intelligent processing.

## 🛠 Tech Stack
- Java
- Spring Boot
- Spring AI
- Ollama (DeepSeek)
- Apache PDFBox

## ✨ Features
- Upload PDF file
- Extract questions using regex
- AI-based topic classification (DeepSeek)
- REST API support

## ⚙️ Setup Instructions

1. Clone the repository:
```bash
git clone https://github.com/YOUR_USERNAME/pdf-extractor-backend.git
Open in IntelliJ
Run the application
Ensure Ollama is running:
ollama run deepseek-r1
📡 API Endpoint
Upload PDF
POST /api/pdf/upload

Form-data:

file: PDF file
🧠 AI Integration

Uses DeepSeek model via Ollama for:

Topic classification
Smart extraction (future scope)
📸 Screenshots

SCREENSHOT1:    https://drive.google.com/file/d/1dzXIIaLuogQriuDfRXxEoiO1EnDDiC6Z/view?usp=sharing

SCREENSHOT2:    https://drive.google.com/file/d/1YH5AqkflxidwFcYE-B199gq5F6a7z4S7/view?usp=sharing

SCREENSHOT3:    https://drive.google.com/file/d/1Rc9GBY0-F_JC4av92O77BdJlXTCbzrtk/view?usp=sharing

👨‍💻 Author

JITENDRA KUMAR
