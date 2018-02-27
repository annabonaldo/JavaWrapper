package Application;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * Created by Anna Bonaldo on 23/02/2018.
 */
public class Student {
    enum Gender {Female, Male};
    enum ItalianKnowledge {italian, one, two, three, four, five};
    String _name;
    Gender _gender;
    Boolean _italian;
    Integer _age;

    /** If _italian == TRUE _nationality and _italianKnowlege are void values
     */
    String _nationality;
    ItalianKnowledge _italianKnowledge;

    public Student(String _name, Gender _gender,Integer _age) {
        this._age = _age;
        this._name = _name;
        this._gender = _gender;
        this._italian = TRUE;
        this._italianKnowledge = ItalianKnowledge.italian;
        this._nationality = "Italiana";
    }

    public Student(String _name, Gender _gender, Boolean _italian, Integer _age, String _nationality, ItalianKnowledge _italianKnowledge)
    {
        this(_name, _gender, _age);

        if(!_italian)
        {
            this._italian = FALSE;
            this._age = _age;
            this._nationality = _nationality;
            this._italianKnowledge = _italianKnowledge;
        }


    }

    public String getAnonymousLine()
    {
        return this._gender+";"+this._age+"; ";
    }

    public String getNameLine()
    {
        return this._name+";";
    }
}
