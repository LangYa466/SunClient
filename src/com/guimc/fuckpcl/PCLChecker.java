package com.guimc.fuckpcl;

import com.guimc.fuckpcl.utils.WindowUtils;

import java.io.File;

/**
 * A library checks Plain Craft Launcher
 * @author guimc, liuli (The UnlegitMC Team), LangYa
 */
public class PCLChecker {
    /**
     * run full PCL check
     * @param mcDir minecraft folder path
     * @param deleteFolder delete PCL data folder for next PCL deleted check
     * @return check result
     */
    public static boolean fullCheck(File mcDir) {
        return fullCheck(mcDir, true);
    }

    /**
     * run full PCL check
     * @param mcDir minecraft folder path
     * @param deleteFolder delete PCL data folder for next PCL deleted check
     * @return check result
     */
    public static boolean fullCheck(File mcDir, boolean deleteFolder) {
        // check if there is a window named PCL
        if (titleCheck()) {
            return true;
        }

        // maybe the window not exists like close the window after launched , so we need to check the PCL data folder
        if (folderCheck(mcDir, deleteFolder)) {
            return true;
        }

        // PCL is not exists in the PC
        return false;
    }

    /**
     * run PCL title check
     * check if there exists a title name contained "Plain Craft Launcher"
     * @return check result
     * @author langya
     */
    public static boolean titleCheck() {
        if (!WindowUtils.isWindows()) {
            return false; // PCL and the native file only support windows
        } else { // PCL Title "Plain Craft Launcher 2"
            String targetStr = "Plain Craft Launcher";
            for (String windowName : WindowUtils.getWindowNames()) {
                if (windowName.length() < targetStr.length() * 2 && windowName.contains(targetStr)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * run PCL data folder check
     * @param mcDir minecraft folder path
     * @param deleteFolder delete PCL data folder for next PCL deleted check
     * @return check result
     */
    public static boolean folderCheck(File mcDir, boolean deleteFolder) {
        if (!mcDir.exists()) {
            throw new IllegalArgumentException("Argument \"mcDir\" is not exists");
        }
        if (!mcDir.isDirectory()) {
            throw new IllegalArgumentException("Argument \"mcDir\" should be a folder");
        }

        boolean exists = false;
        File pclDataDir = new File(mcDir, "PCL");
        if (pclDataDir.exists()) {
            if (deleteFolder) {
                pclDataDir.delete();
            }
            exists = true;
        } // me need to delete all folders

        File mcVersionDir = new File(mcDir, "versions");
        if (mcVersionDir.exists()) { // I think this should be existed but ...
            File[] versionDirs = mcVersionDir.listFiles();
            if (versionDirs != null) {
                for (File versionDir : versionDirs) {
                    File pclVersionDataDir = new File(versionDir, "PCL");
                    if (pclVersionDataDir.exists()) {
                        if (deleteFolder) {
                            pclVersionDataDir.delete();
                        }
                        exists = true;
                    }
                }
            }
        }

        return exists;
    }
}
