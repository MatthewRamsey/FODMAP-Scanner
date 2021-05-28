package com.lemick.fodmapscanner.model.fodmap

class FodmapEntry {
    lateinit var id: String
    lateinit var name: String
    lateinit var fodmap: String
    lateinit var category: String
    lateinit var details: FodmapDetails
}

class FodmapDetails {
    var oligos: Int = 0
    var fructose: Int = 0
    var polyols: Int = 0
    var lactose: Int = 0
};

    
