/* Authors: Do Hoang Khanh & Nguyen Thanh Dat */
package dictionary;

public class TrieNode
{
    /*
        TrieNode class is used to represent nodes in the Trie data structure
        In this code, the target of trie nodes are saving Word objects

        The details of the data structure should be seen in Trie class
    */

    private Word data;              // The word contained in this node as if this node is a terminal node
    private TrieNode[] childNodes;  // The child nodes of this node listed by the ASCII code of
                                    // the character on the connecting edges

    public TrieNode()
    {
        data = null;
        childNodes = new TrieNode[256];
    }

    public Word getData()
    {
        return data;
    }

    public void setData(Word word)
    {
        data = word;
    }

    public TrieNode[] getChildNodes()
    {
        return childNodes;
    }

}
