package com.kandula.swaggerdocgenerator;

import com.intellij.psi.*;

import java.util.List;

class DocumentationGeneratorForVariables {


    DocumentationGeneratorForVariables(List<PsiField> fields, PsiClass parent) {
        execute(fields, parent);
    }

    private void execute(List<PsiField> fields, PsiClass parent) {
        for (PsiField field : fields) {
            setQualifiedName(parent, field);
        }
    }

    private void setQualifiedName(PsiClass parent, PsiField field) {
        String swaggerName = setSwaggerAnnotation(field.getName());
        PsiElementFactory elementFactory = Utilities.getElementFactory(parent);
        PsiAnnotation annotation = elementFactory.createAnnotationFromText(swaggerName, field);
        field.addBefore(annotation, field);
    }

    private String setSwaggerAnnotation(String field) {
        String sentence = Utilities.convertWordToSentence(field);
        return "@ApiModelProperty(value=\"" + sentence + "\")";
    }
}
