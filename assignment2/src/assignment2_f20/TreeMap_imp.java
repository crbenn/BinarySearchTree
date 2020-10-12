package assignment2_f20;

public class TreeMap_imp implements TreeMap {

	TMCell root;
	String[] keys;
	int size;
	TMCell toBeRemoved;
	TMCell loneLeft;
	
	boolean leaf;
	boolean onlyRight;
	boolean onlyLeft;
	boolean both;
	boolean replaceFlag;

	  TreeMap_imp () { 
	    root = null; 
	    size = 0;
	    toBeRemoved = null;
	    loneLeft = null;
	    // for added fields you can add appropriate initialization code here
	  }

	  //-------------------------------------------------------------

	  // dont change, we need this for grading
	  @Override
	  public TMCell getRoot() { return this.root; }
	  
	  //-------------------------------------------------------------
	  // "height" is a complete implementation 
	  // of the public interface method height
	  // it is here to illustrate fr you how the textbook sets up 
	  // the method implementation as recursion
	  // you may include this in your project directly

	  public int height() { // public interface method signature
	  // this method is the public interface method
	  // it is not recursive, but it calls a recursive
	  // private method and passes it access to the tree cells
	    return height_r(this.getRoot());
	  }
	  private int height_r(TMCell c) { 
	  // inner method with different signature
	  // this helper method uses recursion to traverse
	  // and process the recursive structure of the tree of cells
	    if (c==null) return -1;
	    int lht = height_r(c.getLeft());
	    int rht = height_r(c.getRight());
	    return Math.max(lht,rht) + 1;
	  }

	@Override
	public Value put(String k, Value v) {
		
		if (hasKey(k)) {
			return r_putExisting(k, v, this.getRoot());
		} else {
			TMCell newC = new TMCell_imp(k, v);
			size++;
			
			if (size == 1) {
				root = newC;
				return null;
			}
			
			
			
			return r_putNew(k, v, this.getRoot(), newC);
		}
	}
	
	private Value r_putExisting(String k, Value v, TMCell c) { // works
		if (c == null) { return null; }
		
		if (c.getKey().compareTo(k) == 0) { Value old = c.getValue(); c.setValue(v); return old;}
		else if (c.getKey().compareTo(k) > 0) { return r_putExisting(k, v, c.getLeft()); }
		else { return r_putExisting(k, v, c.getRight()); }

	}
	
	private Value r_putNew(String k, Value v, TMCell c, TMCell newC) {
		if (c == null) { return null; }
		
		
		if (c.getKey().compareTo(k) > 0) { 
			if (c.getLeft() == null) {
				c.setLeft(newC);
				return null;
			}
			return r_putNew(k, v, c.getLeft(), newC); 
		} else {
			if (c.getRight() == null) {
				c.setRight(newC);
				return null;
			}
			return r_putNew(k, v, c.getRight(), newC);
		}
	}

	@Override
	public Value get(String k) {
		return r_findKey_returnValue(k, this.getRoot());
	}

	@Override
	public void remove(String k) {
		if (hasKey(k)) {
			
			r_remove(k, this.getRoot());
			size--;
			toBeRemoved = null;
		} 
		
		return;
	}
	
	// largest node in left subtree replaces removed obj if 2 children -- this could mean going left then right then right and so on
	// if only right child, right child replaces
	// if only left child, left child replaces
	// if no children, just delete
	
	private void r_remove(String k, TMCell c) {
	
		if (c == null) { return; }
		
		if (size == 1) { root = null; return; }
		
		
		
		if (toBeRemoved == null) {
			if (c.getKey().compareTo(k) == 0) { toBeRemoved = c; r_remove(k, c); }
			else if (c.getKey().compareTo(k) > 0) { r_remove(k, c.getLeft()); }
			else { r_remove(k, c.getRight()); }
		}
	
		
		if (toBeRemoved != null) {
			
			if (toBeRemoved.equals(c)){
				
				if (c.getLeft() == null) {
					if (c.getRight() == null) {
						leaf = true;
					} else {
						onlyRight = true;
					}
				} else if (c.getRight() == null) {
					onlyLeft = true;
				} else {
					both = true;
				}
			} 
			if (toBeRemoved.equals(this.getRoot())) {
				if (leaf) {
					root = null;
				} else if (onlyLeft) {
					root = c.getLeft();
				} else if (onlyRight) {
					root = c.getRight();
				} else {
					
					TMCell replacement = r_maxCell(toBeRemoved.getLeft());
					TMCell toBePlaced = null;
					
					if (replacement.getLeft() == null) {
						replacement.setLeft(root.getLeft());
						replacement.setRight(root.getRight());
					} else {
						loneLeft = replacement.getLeft();
						
						replacement.setLeft(root.getLeft());
						replacement.setRight(root.getRight());
					}
					
					if (toBeRemoved.getLeft().equals(replacement)) {
						replacement.setLeft(null);
						
						toBePlaced = replacement;
					} 
					
					
					if (loneLeft != null) {
						if (toBePlaced == null) {
							toBePlaced = r_maxCell(replacement.getLeft());
						}
						
						if (toBePlaced.getKey().compareTo(loneLeft.getKey()) > 0) {
							toBePlaced.setLeft(loneLeft);
						} else {
							toBePlaced.setRight(loneLeft);
						}
						
//						if (toBePlaced.getRight() == null) {
//							toBePlaced.setRight(loneLeft);
//						} else if (toBePlaced.getLeft() == null) {
//							toBePlaced.setLeft(loneLeft);
//						}
						
						loneLeft = null;
					}
					
					root = replacement;
					

					
				}
				
				
				toBeRemoved = null;
			} else if (!c.equals(toBeRemoved) && toBeRemoved != null) {
				if (leaf) {
					if (c.getRight() != null && c.getRight().getKey().equals(toBeRemoved.getKey())) {
						c.setRight(null);
						
					} else {
						c.setLeft(null);
					}
						
					leaf = false;
				} else if (onlyLeft) {
					
					if (c.getRight() != null && c.getRight().getKey().equals(toBeRemoved.getKey())) {
						c.setRight(c.getRight().getLeft());
					} else {
						c.setLeft(c.getLeft().getLeft());
					}
						
					onlyLeft = false;
				} else if (onlyRight) {
					
					if (c.getRight() != null && c.getRight().getKey().equals(toBeRemoved.getKey())) {
						
						c.setRight(c.getRight().getRight());
						
					} else {
						c.setLeft(c.getLeft().getRight());
					}

						onlyRight = false;
					} else if (both) {
					
						TMCell replacement = r_maxCell(toBeRemoved.getLeft());
						TMCell toBePlaced = null;

					
						if (replacement.getLeft() == null) {
							replacement.setRight(toBeRemoved.getRight());
						} else {
							loneLeft = replacement.getLeft();
						
							replacement.setLeft(toBeRemoved.getLeft());
							replacement.setRight(toBeRemoved.getRight());
						}
					
						if (replacement.equals(toBeRemoved.getLeft())) {
							replacement.setRight(toBeRemoved.getRight());
							replacement.setLeft(null);
							
							toBePlaced = replacement;
						} else {
							replacement.setRight(toBeRemoved.getRight());
							replacement.setLeft(toBeRemoved.getLeft());
						}
					
						
						
						if (c.getRight().getKey().equals(toBeRemoved.getKey())) {
							c.setRight(replacement);
						} else {
							c.setLeft(replacement);
						}
						
						toBeRemoved = replacement;
						
						if (loneLeft != null) {
							if (toBePlaced == null) {
								toBePlaced = r_maxCell(replacement.getLeft());
							}
							
							if (toBePlaced.getKey().compareTo(loneLeft.getKey()) > 0) {
								toBePlaced.setLeft(loneLeft);
							} else {
								toBePlaced.setRight(loneLeft);
							}
							
							loneLeft = null;
						}
						
					}
				
				
				toBeRemoved = null;
			}
			return;	
		}
	}
	
		
	

	@Override
	public boolean hasKey(String k) {
		if (r_findKey_returnKey(k, this.getRoot()) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	private Value r_findKey_returnValue(String k, TMCell c) {
		if (c == null) { return null; };
		
		Value target;
		
		if (c.getKey().compareTo(k) == 0) { return c.getValue(); }
		else if (c.getKey().compareTo(k) > 0) { target = r_findKey_returnValue(k, c.getLeft()); }
		else { target = r_findKey_returnValue(k, c.getRight()); }
		
		return target;
	}
	
	private String r_findKey_returnKey(String k, TMCell c) {
		if (c == null) { return null; };
		
		String target;
		
		if (c.getKey().compareTo(k) == 0) { return c.getKey(); }
		else if (c.getKey().compareTo(k) > 0) { target = r_findKey_returnKey(k, c.getLeft()); }
		else { target = r_findKey_returnKey(k, c.getRight()); }
		
		return target;
	}

	@Override
	public int size() {
		return size;
	}

	private TMCell r_maxCell(TMCell c) {
		if (c == null) { return null; };
		if (c.getRight() == null) { return c; };
		
		return r_maxCell(c.getRight());
	}
	
	@Override
	public String maxKey() {
		return r_Max(this.getRoot());
	}
	
	private String r_Max(TMCell c) { // works!
		if (c == null) { return null; };
		if (c.getRight() == null) { return c.getKey(); };
		
		return r_Max(c.getRight());
	}

	@Override
	public String minKey() { // works!
		return r_Min(this.getRoot());
	}
	
	private String r_Min(TMCell c) {
		if (c == null) { return null; };
		if (c.getLeft() == null) { return c.getKey(); };
		
		
		return r_Min(c.getLeft());
	}

	@Override
	public String[] getKeys() {  // works!
		keys = new String[size()];
		
		r_Keys(this.getRoot(), 0);
		
		return keys;
	}
	
	private int r_Keys(TMCell c, int i) {
		if (c == null) {
			return i;
		}
		i = r_Keys(c.getLeft(), i); 
		keys[i] = c.getKey();
		i++;
		i = r_Keys(c.getRight(), i);
		return i;
	}
	
}
