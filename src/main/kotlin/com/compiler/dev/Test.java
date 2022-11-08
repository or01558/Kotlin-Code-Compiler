package com.compiler.dev;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.io.IOException;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

class ExceptionThrowerMethod extends MethodVisitor {
    private final MethodVisitor target;

    public ExceptionThrowerMethod(MethodVisitor methodVisitor) {
        super(ASM4, null);
        this.target=methodVisitor;
    }

    @Override
    public void visitCode() {
        target.visitCode();
        target.visitTypeInsn(NEW, "java/io/IOException");
        target.visitInsn(DUP);
        target.visitMethodInsn(INVOKESPECIAL,"java/io/IOException","<init>","()V",false);
        target.visitInsn(ATHROW);
        target.visitMaxs(2, 0);
        target.visitEnd();
    }
}

public class Test {

    public static void main(String[] args) throws IOException {
         ClassReader reader = new ClassReader("className");
        ClassWriter writer = new ClassWriter(reader, 0);
        MethodVisitor visitor = writer.visitMethod(0, "test", "", "", new String[]{});
        visitor.visitCode();
    }
}
