package br.edu.fatecararas.springplayground.model;

public class Note {
    public String content;

    public Note(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Note [content=" + content + "]";
    }   
    
    
}
