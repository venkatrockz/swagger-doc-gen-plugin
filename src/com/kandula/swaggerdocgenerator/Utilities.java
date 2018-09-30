package com.kandula.swaggerdocgenerator;

import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElementFactory;

public class Utilities {

    public static String convertWordToSentence(String word) {
        word = word.replaceAll(Constants.REGEX, Constants.REPLACE_STRING).toLowerCase();
        word = capitalizeFirstLetter(word);
        return word;
    }

    private static String capitalizeFirstLetter(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    public static PsiElementFactory getElementFactory(PsiClass parent) {
        return JavaPsiFacade.getElementFactory(parent.getProject());
    }
}
    
    
