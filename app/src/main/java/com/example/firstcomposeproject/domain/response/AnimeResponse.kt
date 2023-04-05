package com.example.firstcomposeproject.domain.response

import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.StateFlow

data class AnimeResponse(
    var data: List<DataResponse>
)
