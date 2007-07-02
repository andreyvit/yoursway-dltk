set date(DAY_OF_WEEK_IN_MONTH) \
    [expr { ( ( $date(DAY_OF_MONTH) - 1 ) / 7) + 1 }]

