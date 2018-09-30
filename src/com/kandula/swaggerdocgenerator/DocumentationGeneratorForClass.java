package com.kandula.swaggerdocgenerator;

import com.intellij.psi.*;

import java.util.List;

class DocumentationGeneratorForClass {

    DocumentationGeneratorForClass(List<PsiMember> members, PsiClass parent) {
        execute(parent, members);
    }

    private void execute(PsiClass parent, List<PsiMember> members) {
        setQualifiedName(parent, members.get(0));
    }

    private void setQualifiedName(PsiClass parent, PsiMember member) {
        String swaggerName = getSwaggerAnnotationForClass(member.getName());
        PsiElementFactory elementFactory = Utilities.getElementFactory(parent);
        PsiAnnotation annotation = elementFactory.createAnnotationFromText(swaggerName, parent);
        parent.addBefore(annotation, member.getFirstChild());
    }

    private String getSwaggerAnnotationForClass(String field) {
        String sentence = Utilities.convertWordToSentence(field);
        return "@ApiModel(value = \"" + sentence + "\", description = \"" + sentence + "\")";
    }
}
    
    
