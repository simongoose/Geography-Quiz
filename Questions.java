import java.util.HashMap;   // import the hashmap class
/**
 * Creates questions in hashmaps based on difficulty
 * Returns questions to be asked to the user
 *
 * @author Simon Lee
 * @version 02/09/2021
 */
public class Questions
{
    // fields
    int[] difficulties = {1, 2, 3, 4};  // stores the 4 values of difficulties as indexes
    
    // create hashmaps
    HashMap<String, String> easyQuestions = 
            new HashMap<String, String>();
    HashMap<String, String> mediumQuestions = 
            new HashMap<String, String>();
    HashMap<String, String> hardQuestions = 
            new HashMap<String, String>();
    HashMap<String, String> expertQuestions = 
            new HashMap<String, String>();
    
    /**
     * Constructor for objects of class Questions
     * 
     * Creates 4 hashmaps for the 4 difficulties of questions
     * Stores all questions in (country, capital) format
     */
    public Questions()
    { 
        // add keys and values (country, capital)
        // easy questions
        easyQuestions.put("Argentina", "Buenos Aires");
        easyQuestions.put("Australia", "Canberra");
        easyQuestions.put("Austria", "Vienna");
        easyQuestions.put("Bangladesh", "Dhaka");
        easyQuestions.put("Belgium", "Brussels");
        easyQuestions.put("Brazil", "Brasilia");
        easyQuestions.put("Canada", "Ottawa");
        easyQuestions.put("China", "Beijing");
        easyQuestions.put("Czech Republic", "Prague");
        easyQuestions.put("Denmark", "Copenhagen");
        easyQuestions.put("Egypt", "Cairo");
        easyQuestions.put("France", "Paris");
        easyQuestions.put("Germany", "Paris");
        easyQuestions.put("Greece", "Athens");
        easyQuestions.put("Hungary", "Budapest");
        easyQuestions.put("India", "New Delhi");
        easyQuestions.put("Indonesia", "Jakarta");
        easyQuestions.put("Ireland", "Dublin");
        easyQuestions.put("Israel", "Jerusalem");
        easyQuestions.put("Italy", "Rome");
        easyQuestions.put("Japan", "Tokyo");
        easyQuestions.put("Malaysia", "Kuala Lumpur");
        easyQuestions.put("Mexico", "Mexico City");
        easyQuestions.put("Netherlands", "Amsterdam");
        easyQuestions.put("New Zealand", "Wellington");
        easyQuestions.put("Panama", "Panama City");
        easyQuestions.put("Russia", "Moscow");
        easyQuestions.put("Saudi Arabia", "Riyadh");
        easyQuestions.put("Singapore", "Singapore");
        easyQuestions.put("South Africa", "Bloemfontein/Pretoria/Johannesburg");
        easyQuestions.put("South Korea", "Seoul");
        easyQuestions.put("Spain", "Madrid");
        easyQuestions.put("Thailand", "Bangkok");
        easyQuestions.put("Ukraine", "Kyiv");
        easyQuestions.put("United Arab Emirates", "Abu Dhabi");
        easyQuestions.put("United Kingdom", "London");
        easyQuestions.put("USA", "Washington DC");
        easyQuestions.put("Vietnam", "Hanoi");
        
        // add keys and values (country, capital)
        // medium questions
        mediumQuestions.put("Afghanistan", "Kabul");
        mediumQuestions.put("Algeria", "Algiers");
        mediumQuestions.put("Azerbaijan", "Baku");
        mediumQuestions.put("Belarus", "Minsk");
        mediumQuestions.put("Bolivia", "La Paz/Sucre");
        mediumQuestions.put("Bosnia and Herzegovina", "Sarajevo");
        mediumQuestions.put("Bulgaria", "Sofia");
        mediumQuestions.put("Cambodia", "Phnom Penh");
        mediumQuestions.put("Chile", "Santiago");
        mediumQuestions.put("Colombia", "Bogota");
        mediumQuestions.put("Costa Rica", "San Jose");
        mediumQuestions.put("Croatia", "Zagreb");
        mediumQuestions.put("Cuba", "Havana");
        mediumQuestions.put("Democratic Republic of the Congo", "Kinshasa");
        mediumQuestions.put("DPRK/North Korea", "Pyongyang");
        mediumQuestions.put("Estonia", "Tallinn");
        mediumQuestions.put("Ethiopia", "Addis Ababa");
        mediumQuestions.put("Finland", "Helsinki");
        mediumQuestions.put("Guatemala", "Guatemala City");
        mediumQuestions.put("Iceland", "Reykjavik");
        mediumQuestions.put("Iran", "Tehran");
        mediumQuestions.put("Iraq", "Baghdad");
        mediumQuestions.put("Jamaica", "Kingston");
        mediumQuestions.put("Kenya", "Nairobi");
        mediumQuestions.put("Kuwait", "Kuwait City");
        mediumQuestions.put("Kyrgyzstan","Bishkek");
        mediumQuestions.put("Latvia", "Riga");
        mediumQuestions.put("Lebanon", "Beirut");
        mediumQuestions.put("Luxembourg", "Luxembourg");
        mediumQuestions.put("Monaco", "Monaco");
        mediumQuestions.put("Morocco", "Rabat");
        mediumQuestions.put("Nepal", "Kathmandu");
        mediumQuestions.put("Nigeria", "Abuja");
        mediumQuestions.put("Norway", "Oslo");
        mediumQuestions.put("Pakistan", "Islamabad");
        mediumQuestions.put("Peru", "Lima");
        mediumQuestions.put("Philippines", "Manila");
        mediumQuestions.put("Poland", "Warsaw");
        mediumQuestions.put("Portugal", "Lisbon");
        mediumQuestions.put("Qatar", "Doha");
        mediumQuestions.put("Romania", "Bucharest");
        mediumQuestions.put("San Marino", "San Marino");
        mediumQuestions.put("Sao Tome and Principe", "Sao Tome");
        mediumQuestions.put("Serbia", "Belgrade");
        mediumQuestions.put("Sweden", "Stockholm");
        mediumQuestions.put("Switzerland", "Bern");
        mediumQuestions.put("Tunisia", "Tunis");
        mediumQuestions.put("Turkey", "Ankara");
        mediumQuestions.put("Uruguay", "Montevideo");
        mediumQuestions.put("Venezuela", "Caracas");
        
        // add keys and values (country, capital)
        // hard questions
        hardQuestions.put("Albania", "Tirana");
        hardQuestions.put("Andorra", "Andorra la Vella");
        hardQuestions.put("Angola", "Luanda");
        hardQuestions.put("Armenia", "Yerevan");
        hardQuestions.put("Bahamas", "Nassau");
        hardQuestions.put("Bahrain", "Manama");
        hardQuestions.put("Belize", "Belmopan");
        hardQuestions.put("Botswana", "Gaborone");
        hardQuestions.put("Burkina Faso","Ouagadougou");
        hardQuestions.put("Cabo Verde", "Praia");
        hardQuestions.put("Cameroon", "Yaounde");
        hardQuestions.put("Cote D'Ivoire", "Yamoussoukro");
        hardQuestions.put("Cyprus", "Nicosia");
        hardQuestions.put("Djibouti", "Djibouti");
        hardQuestions.put("Dominican Republic", "Santo Domingo");
        hardQuestions.put("Ecuador", "Quito");
        hardQuestions.put("Fiji", "Suva");
        hardQuestions.put("Georgia", "Tbilisi");
        hardQuestions.put("Ghana", "Accra");
        hardQuestions.put("Guinea", "Conakry");
        hardQuestions.put("Guinea Bissau", "Bissau");
        hardQuestions.put("Haiti", "Port-au-Prince");
        hardQuestions.put("Honduras", "Tegucigalpa");
        hardQuestions.put("Jordan", "Amman");
        hardQuestions.put("Kazakhstan", "Nur Sultan");
        hardQuestions.put("Laos", "Vientiane");
        hardQuestions.put("Lesotho", "Maseru");
        hardQuestions.put("Lithuania", "Vilnius");
        hardQuestions.put("Madagascar", "Antananarivo");
        hardQuestions.put("Maldives", "Male");
        hardQuestions.put("Malta", "Valletta");
        hardQuestions.put("Mauritius", "Port Louis");
        hardQuestions.put("Micronesia", "Palikir");
        hardQuestions.put("Moldova", "Chisinau");
        hardQuestions.put("Mongolia", "Ulan Bator");
        hardQuestions.put("Montenegro", "Podgorica");
        hardQuestions.put("Namibia", "Windhoek");
        hardQuestions.put("Nicaragua", "Managua");
        hardQuestions.put("Niger", "Niamey");
        hardQuestions.put("North Macedonia", "Skopje");
        hardQuestions.put("Oman", "Muscat");
        hardQuestions.put("Papua New Guinea", "Port Moresby");
        hardQuestions.put("Paraguay", "Asuncion");
        hardQuestions.put("Rwanda", "Kigali");
        hardQuestions.put("Samoa", "Apia");
        hardQuestions.put("Senegal", "Dakar");
        hardQuestions.put("Sierra Leone", "Freetown");
        hardQuestions.put("Slovakia", "Bratislava");
        hardQuestions.put("Slovenia", "Ljubljana");
        hardQuestions.put("Sri Lanka", "Colombo/Sri Jayawardenepura Kotte");
        hardQuestions.put("Sudan", "Khartoum");
        hardQuestions.put("Syria", "Damascus");
        hardQuestions.put("Tanzania", "Damascus");
        hardQuestions.put("Tonga", "Nuku'alofa");
        hardQuestions.put("Trinidad and Tobago", "Port of Spain");
        hardQuestions.put("Turkmenistan", "Ashgabat");
        hardQuestions.put("Uganda", "Kampala");
        hardQuestions.put("Yemen", "Sana'a");
        
        // add keys and values (country, capital)
        // expert questions
        expertQuestions.put("Antigua and Barbuda", "St John's");
        expertQuestions.put("Barbados", "Bridgetown");
        expertQuestions.put("Benin", "Porto Novo");
        expertQuestions.put("Bhutan", "Thimphu");
        expertQuestions.put("Brunei", "Bandar Seri Begawan");
        expertQuestions.put("Burundi", "Gitega");
        expertQuestions.put("Central African Republic", "Bangui");
        expertQuestions.put("Chad", "N'Djamena");
        expertQuestions.put("Comoros", "Moroni");
        expertQuestions.put("Congo (Republic of the)", "Brazzaville");
        expertQuestions.put("Dominica", "Roseau");
        expertQuestions.put("El Salvador", "San Salvador");
        expertQuestions.put("Equatorial Guinea", "Malabo");
        expertQuestions.put("Eritrea", "Asmara");
        expertQuestions.put("Eswatini", "Mbabane");
        expertQuestions.put("Gabon", "Libreville");
        expertQuestions.put("Gambia", "Banjul");
        expertQuestions.put("Grenada", "St George");
        expertQuestions.put("Guyana", "Georgetown");
        expertQuestions.put("Kiribati", "Tarawa");
        expertQuestions.put("Liberia", "Monrovia");
        expertQuestions.put("Libya", "Tripoli");
        expertQuestions.put("Liechtenstein", "Vaduz");
        expertQuestions.put("Malawi", "Lilongwe");
        expertQuestions.put("Mali", "Bamako");
        expertQuestions.put("Marshall Islands", "Majuro");
        expertQuestions.put("Mauritania", "Nouakchott");
        expertQuestions.put("Mozambique", "Maputo");
        expertQuestions.put("Myanmar", "Nay Pyi Taw");
        expertQuestions.put("Nauru", "Yaren");
        expertQuestions.put("Palau", "Ngerulmud");
        expertQuestions.put("St Kitts and Nevis", "Basseterre");
        expertQuestions.put("St Lucia", "Castries");
        expertQuestions.put("St Vincent and the Grenadines", "Kingstown");
        expertQuestions.put("Seychelles", "Victoria");
        expertQuestions.put("Solomon Islands", "Honiara");
        expertQuestions.put("Somalia", "Mogadishu");
        expertQuestions.put("South Sudan", "Juba");
        expertQuestions.put("Suriname", "Paramaribo");
        expertQuestions.put("Tajikistan", "Dushanbe");
        expertQuestions.put("Timor-Leste", "Dili");
        expertQuestions.put("Togo", "Lome");
        expertQuestions.put("Tuvalu", "Funafuti");
        expertQuestions.put("Uzbekistan", "Tashkent");
        expertQuestions.put("Vanuatu", "Port Vila");
        expertQuestions.put("Zambia", "Lusaka");
        expertQuestions.put("Zimbabwe", "Harare");
    }
    
    /**
     * Returns the desired hashmap
     * 
     * @return HashMap - the hashmap for the desired difficulty
     */
    public HashMap getQuestions(int difficulty) {
        if (difficulty == difficulties[0]) {
            return easyQuestions;
        } else if (difficulty == difficulties[1]) {
            return mediumQuestions;
        } else if (difficulty == difficulties[2]) {
            return hardQuestions;
        } else {
            return expertQuestions;
        }
    }
}
