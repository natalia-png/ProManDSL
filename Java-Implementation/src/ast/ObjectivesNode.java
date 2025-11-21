package ast;

import java.util.ArrayList;

public class ObjectivesNode {
   private String general;
   private ArrayList<String> specifics;

   public ObjectivesNode(String var1, ArrayList<String> var2) {
      this.general = var1;
      this.specifics = var2;
   }

   // Getters originales
   public String getGeneral() {
      return this.general;
   }

   public ArrayList<String> getSpecifics() {
      return this.specifics;
   }

   // ✅ Getters nuevos para HtmlGenerator
   public String getGeneralObjective() {
      return this.general;
   }

   public ArrayList<String> getSpecificObjectives() {
      return this.specifics;
   }
}
