package assignment2_f20;

public class BST_Playground {

	/*
	 * you will test your own TreeMap implementation in here
	 *
	 * we will replace this with our own when grading, and will
	 * do what you should do in here... create TreeMap objects,
	 * put data into them, take data out, look for values stored
	 * in it, checking size, and looking at the TMCells to see if they 
	 * are all linked up correctly as a BST
	 * 
	*/
	  
	  public static void main(String[] args) {
	    // you should test your TreeMap implementation in here
	    // sample tests are shown

	    // it is up to you to test it thoroughly and make sure
	    // the methods behave as requested above in the interface
	  
	    // do not simple depend on the oracle test we will give
	    // use the oracle tests as a way of checking AFTER you have done
	    // your own testing

	    // one thing you might find useful for debugging is a print tree method
	    // feel free to use the one we have here ... basic and quick, or write one 
	    // you like better, one that shows you the tree structure more clearly
	    // the one we have here only shows keys, you may wish to add 
	    // value fields to the printing

	    TreeMap tm = new TreeMap_imp();
	    Value v1 = new Value_imp(12345, 87.3, 21);
	    Value v2 = new Value_imp(23456, 75.54, 25);
	    Value v3 = new Value_imp(34567, 99.013, 19);
	    Value v4 = new Value_imp(45678, 55.70, 35);
	    Value v5 = new Value_imp(44444, 55.2, 33);
	    
//	    tm.put("balpha", v1);
//	    tm.put("dalpha", v1);
	    tm.put("f", v1);
	    tm.put("t", v2);
	    tm.put("d", v2);
	    tm.put("c", v2);
	    tm.put("h", v2);
	    tm.put("g", v2);
	    tm.put("u", v3);
	    tm.put("a", v1);
	    tm.put("b", v3);
	    
	    tm.remove("t");
	    String[] keysArray = tm.getKeys();
	    System.out.println("keys after removing t: ");
	    for (int i = 0; i < tm.size(); i++) {
	    	System.out.println(keysArray[i]);
	    }
	    tm.remove("a");
	    keysArray = tm.getKeys();
	    System.out.println("keys after removing a: ");
	    for (int i = 0; i < tm.size(); i++) {
	    	System.out.println(keysArray[i]);
	    }
	    tm.remove("c");
	    keysArray = tm.getKeys();
	    System.out.println("keys after removing c: ");
	    for (int i = 0; i < tm.size(); i++) {
	    	System.out.println(keysArray[i]);
	    }
	    tm.remove("f");
	    keysArray = tm.getKeys();
	    System.out.println("keys after removing f: ");
	    for (int i = 0; i < tm.size(); i++) {
	    	System.out.println(keysArray[i]);
	    }
	    tm.remove("g");
	    keysArray = tm.getKeys();
	    System.out.println("keys after removing g: ");
	    for (int i = 0; i < tm.size(); i++) {
	    	System.out.println(keysArray[i]);
	    }
	    tm.remove("u");
	    keysArray = tm.getKeys();
	    System.out.println("keys after removing u: ");
	    for (int i = 0; i < tm.size(); i++) {
	    	System.out.println(keysArray[i]);
	    }
	    tm.remove("h");
	    keysArray = tm.getKeys();
	    System.out.println("keys after removing h: ");
	    for (int i = 0; i < tm.size(); i++) {
	    	System.out.println(keysArray[i]);
	    }
	    
//	    tm.remove("t");
//	    System.out.println("should be h " + tm.getRoot().getRight().getKey());
////	    System.out.println("should be g " + tm.getRoot().getRight().getLeft().getKey());
//	    System.out.println("should be u " + tm.getRoot().getRight().getRight().getKey());
////	    tm.remove("h");
////	    System.out.println(tm.getRoot().getKey());
//	    System.out.println(tm.getRoot().getRight().getKey());

	    keysArray = tm.getKeys();
	    System.out.println("keys: ");
	    for (int i = 0; i < tm.size(); i++) {
	    	System.out.println(keysArray[i]);
	    }
	    tm.remove("c");
//	    tm.remove("g");
	    
	    System.out.println(tm.hasKey("f"));
	    System.out.println(tm.hasKey("t"));
	    System.out.println(tm.hasKey("c"));
	    System.out.println(tm.hasKey("h"));
	    System.out.println(tm.hasKey("g"));
	    System.out.println(tm.hasKey("u"));
	    System.out.println(tm.hasKey("a"));
	    System.out.println(tm.hasKey("b"));
	    System.out.println(tm.hasKey("d"));
	    
	    System.out.println(tm.getRoot().getKey());
	    
	   keysArray = tm.getKeys();
//	    
	   System.out.println("keys: ");
	    for (int i = 0; i < tm.size(); i++) {
	    	System.out.println(keysArray[i]);
	    }
	    
	    System.out.println("Max: " + tm.maxKey());
	    System.out.println("Min: " + tm.minKey());
	    System.out.println("Height: " + tm.height());
	    System.out.println("Size: " + tm.size());
	    System.out.println("Root: " + tm.getRoot().getKey());
	    tm.put("f", v2);
	    System.out.println("should be 23456: " + tm.getRoot().getValue().getIdNum());
	    
		   keysArray = tm.getKeys();
//		    
		   System.out.println("keys: ");
		    for (int i = 0; i < tm.size(); i++) {
		    	System.out.println(keysArray[i]);
		    }
		    System.out.println(tm.getRoot().getRight().getKey());
//	    
////	    System.out.println("done");
////	    System.out.println(tm.maxKey());
//	    System.out.println(tm.minKey()); // assumes a toString for a Value object
////	    System.out.println(tm.hasKey("alpha"));
	    prTree(tm.getRoot(),0); 

	    // etc...
	    
	    TreeMap tm2 = new TreeMap_imp();
	    
	    System.out.println("should be null: " + tm2.maxKey());
	    System.out.println("should be null: " + tm2.minKey());

	    tm2.put("q", v1);
	    tm2.put("r", v1);
	    tm2.put("s", v1);
	    tm2.put("t", v1);
	    tm2.put("w", v2);
	    tm2.put("x", v1);
	    
	    System.out.println("should be 5: " + tm2.height());
	    System.out.println("should be 6: " + tm2.size());
	    
	    String[] keysArray2 = tm2.getKeys();
	    System.out.println("keys:");
	    for (int i = 0; i < tm2.size(); i++) {
	    	System.out.println(keysArray2[i]);
	    }
	    
	    tm2.remove("q");
	    tm2.remove("r");
	    tm2.remove("s");
	    tm2.remove("t");
	    tm2.remove("w");
	    
	    keysArray2 = tm2.getKeys();
	    System.out.println("keys:");
	    for (int i = 0; i < tm2.size(); i++) {
	    	System.out.println(keysArray2[i]);
	    }
	    
	    tm2.remove("x");
	    
	    keysArray2 = tm2.getKeys();
	    System.out.println("keys:");
	    for (int i = 0; i < tm2.size(); i++) {
	    	System.out.println(keysArray2[i]);
	    }
	    
	    System.out.println("should be null: " + tm2.getRoot());

	  }

	  public static void prTree (TMCell c, int off) {
	    if (c==null) return;
	        
	    prTree(c.getRight(), off+2);
	    
	    for (int i=0; i<off; i++) System.out.print(" ");
	    System.out.println(c.getKey());
	    
	    prTree(c.getLeft(), off+2);
	  }
	
}
